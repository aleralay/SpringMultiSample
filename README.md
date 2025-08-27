# SpringMultiSample

`Spring Boot`を使用したマルチモジュールプロジェクトのサンプル

システム共通モジュールとサブシステム共通モジュール、データベースモジュール、ロジックモジュール

各起動用モジュールで構成されたマルチモジュールプロジェクトになっている。

```bash
mvn -B archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=springsample -DartifactId=sys-common -Dversion=1.0-SNAPSHOT
mvn -B archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=springsample -DartifactId=subsys-common -Dversion=1.0-SNAPSHOT
mvn -B archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=springsample -DartifactId=subsys-database -Dversion=1.0-SNAPSHOT
mvn -B archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=springsample -DartifactId=subsys-logic -Dversion=1.0-SNAPSHOT
mvn -B archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=springsample -DartifactId=subsys-a-boot -Dversion=1.0-SNAPSHOT
mvn -B archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=springsample -DartifactId=subsys-b-boot -Dversion=1.0-SNAPSHOT
mvn -B archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=springsample -DartifactId=subsys-c-boot -Dversion=1.0-SNAPSHOT
mvn -B archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=springsample -DartifactId=sys-sqs-pooling -Dversion=1.0-SNAPSHOT
```

