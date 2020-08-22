# Scala x DDD sample

Scalaを使ったDDDのサンプル

## 概要
ユーザーがタスクを取得/作成/削除するシンプルなケースをDDDで実装する

## 技術スタック
- Scala v2.12.8
- PlayFramework v2.8
- cats
- Eff
- Domain-Driven Design
- CQS
- Docker

## 構成

```
├── Dockerfile
├── DockerfileDb
├── README.md
├── app
│    ├── Module.scala // DI
│    ├── adapter // Adapter layer(e.g. controllers)
│    ├── domain // Domain layer
│    ├── infrastructure // infra layer(e.g. DTO, repositoryImpl)
│    └── usecase // useCase(application) layer
├── bin // 動作確認用のツールなど
├── build.sbt
├── codegen
├── conf
├── docker-compose.yml
...
```

## 起動
- Dockerで全部動かしたい場合
    - `docker-compose up`
- PlayFrameworkはローカルで動かしたい場合
    - `bin/run.sh`

## Seed
- `bin/seed.sh`でサンプルデータを投入できる

## API動作確認
### ハンター主体
- ハンターがモンスターを攻撃する
    - `bin/attackMonster.sh`

### モンスター主体

## DBマイグレーション
- [evolutions](https://www.playframework.com/documentation/2.8.x/Evolutions) と [slick-codegen](https://scala-slick.org/doc/3.2.0/code-generation.html) を使っている
- `conf/evolutions/default` 配下にSQLファイルを置いてサーバーを起動することで、DBマイグレーションが実行される
- マイグレーション結果をコードに反映させるには `sbt codegen/run` を行う
