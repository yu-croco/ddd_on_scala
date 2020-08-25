package adapter.controllers.hunter

import adapter.controllers.FutureOps
import adapter.controllers.helpers.JsonHelper
import adapter.json.hunter.getmaterial.{AttackMonsterRequest, GetMaterialFromMonsterJson, ToJson}
import com.google.inject.Inject
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.all.toEitherEffectOps
import org.atnos.eff.syntax.future.toFutureOps
import org.atnos.eff.{ExecutorServices, Fx, TimedFuture}
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import usecase.helper.UseCaseError
import usecase.hunter.GetMaterialFromMonsterUseCase
import usecase.usecase.UseCaseEither

import scala.concurrent.{ExecutionContext, Future}

class GetMaterialFromMonsterController @Inject()(cc: ControllerComponents, useCase: GetMaterialFromMonsterUseCase)(
    implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with JsonHelper
    with ToJson {

  def create(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext
    val body                          = request.body.validate[GetMaterialFromMonsterJson]

    body.fold(
      e => Future.successful(toRequestJsonTypeError(e)),
      value => {
        AttackMonsterRequest
          .convertToEntity(value)
          .fold(
            e => Future.successful(toVOConvertError(e)),
            vo =>
              useCase
                .run[Fx.fx2[UseCaseEither, TimedFuture]](vo.hunterId, vo.monsterId)
                .runEither[UseCaseError]
                .runAsync
                .flatMap {
                  case Right(value) => Future.successful(value)
                  case Left(e)      => Future.failed(e)
                }
                .toCreateResponse
          )
      }
    )
  }
}