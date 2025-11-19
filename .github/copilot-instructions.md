<!--
This file is maintained by an AI assistant. Please review changes before committing.
-->
# Copilot instructions for SpringMultiSample

簡潔に：このリポジトリは「ルート `pom.xml` による Maven マルチモジュール構成」で、`subsys-*-boot` が起動モジュール、`sys-*` と `subsys-*` がライブラリ/共通モジュールです。

- **重要ファイル**:
  - ルート `pom.xml` — モジュール一覧（`<modules>`）と親 Spring Boot バージョン（`3.5.0`）を管理。
  - `sys-common/` — システム共通インタフェース（例: `sys-common/src/main/java/springsample/common/SQSLogicInterface.java`）。
  - `subsys-common/` — サブシステム共通設定・メッセージ（例: `subsys-common/src/main/resources/message.yaml`）。
  - `subsys-*-boot/` — 起動可能な Spring Boot アプリ（例: `subsys-a-boot/src/main/java/.../ScheduleFunctionA.java`）。
  - `sys-sqs-pooling/` — SQS ポーリングの実装と統合点（例: `SQSPollingService` が `SQSLogicInterface` 実装を取得して呼び出す）。

- **アーキテクチャの要点（AI向け）**:
  - モノリス風のマルチモジュール設計：ビルドはルートで行い、個別起動は各 `-boot` モジュールで行う。
  - クロスモジュール呼び出しは Spring の DI/Bean 名で行われる（例: `ApplicationContext#getBean("BusinessLogicA")` を使う箇所がある）。
  - メッセージは YAML リソースで管理（例: `sysmessage.yaml`, `message.yaml`）。

- **よく使うコマンド（確実に動く最小セット）**:
  - 全モジュールをビルド: `mvn -DskipTests clean install`（ルートで実行）
  - 個別モジュールをビルドして依存も一緒に作る: `mvn -pl subsys-a-boot -am package`（`-am` = also make）
  - 起動（開発時）: ルートまたはモジュールで `mvn -pl subsys-a-boot -am spring-boot:run`
  - テスト実行: `mvn test` または `mvn -pl <module> test`
  - 注意: Java 17 を使用する（`pom.xml` の `<java.version>`）

- **プロジェクト固有のパターン／注意点**:
  - ルート `pom.xml` の `<modules>` にモジュールを追加することでプロジェクトに参加させる。
  - 起動モジュールはアプリケーションコードとスケジューラを持ち、`subsys-logic` のビジネスロジックを注入して呼ぶ設計。
  - SQS や定期処理の統合は `sys-sqs-pooling` と `sys-common` のインタフェースを通じて行われるため、インタフェース名や Bean 名（例: `BusinessLogicA`）を壊さないこと。

- **編集時の安全ルール（AIが自動編集する際のガイド）**:
  - 変更箇所のビルドと単体起動を忘れない：`mvn -pl <changed-module> -am package` と `spring-boot:run`。
  - 共有インタフェース（`sys-common`）のシグネチャ変更は影響範囲が広い。先に依存モジュールを更新するか、インタフェース新設を検討する。
  - メッセージキーは YAML に定義。キー変更は参照元のログや `SysMessages` クラスを全検索して更新する。

- **参照例（コードベースに基づく短いサンプル）**:
  - SQS 呼び出し例: `sys-sqs-pooling/src/main/java/.../SQSPollingService.java` は `ApplicationContext#getBean("BusinessLogicA")` を使って `SQSLogicInterface` 実装を呼ぶ。
  - スケジューラ例: `subsys-a-boot/src/main/java/springsample/subsys/component/ScheduleFunctionA.java` は `BusinessLogicA` を `@Autowired` して `logic.execute()` を定期実行する。

---
もしこの内容で足りない箇所（例：外部 SQS 設定、CI パイプライン、環境変数の場所など）があれば教えてください。すぐに追記します。
