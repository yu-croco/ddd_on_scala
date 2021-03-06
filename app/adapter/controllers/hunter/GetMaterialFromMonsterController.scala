package adapter.controllers.hunter

import adapter.controllers.CirceCirceJsonOps.FutureJsonOps
import adapter.controllers.FutureEitherStack
import adapter.controllers.helpers.JsonHelper
import adapter.json.hunter.getmaterial.{AttackMonsterRequest, GetMaterialFromMonsterJson, ToJson}
import com.google.inject.Inject
import io.circe.generic.auto._
import io.circe.syntax._
import org.atnos.eff.ExecutorServices
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.all.toEitherEffectOps
import org.atnos.eff.syntax.future.toFutureOps
import play.api.libs.circe.Circe
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import usecase.helpers.UseCaseError
import usecase.hunter.GetMaterialFromMonsterUseCase

import scala.concurrent.{ExecutionContext, Future}

class GetMaterialFromMonsterController @Inject()(cc: ControllerComponents, useCase: GetMaterialFromMonsterUseCase)(
    implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with JsonHelper
    with ToJson
    with Circe {

  def create(hunterId: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext
    val body                          = request.body.validate[GetMaterialFromMonsterJson]

    body.fold(
      e => Future.successful(e.toRequestJsonTypeError),
      value =>
        AttackMonsterRequest
          .convertToEntity(value, hunterId)
          .fold(
            e => Future.successful(e.toVOConvertError),
            vo =>
              useCase
                .program[FutureEitherStack](vo.hunterId, vo.monsterId)
                .runEither[UseCaseError]
                .runAsync
                .flatMap {
                  case Right(value) => Future.successful(value.asJson)
                  case Left(e)      => Future.failed(e)
                }
                .toCreateResponse
        )
    )
  }
}
