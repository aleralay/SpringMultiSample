---
applyTo: **/*Mapper.xml
---

コードをチェックするときは、次の事項に準拠していることを確認してください:

## Semantic XML First
- XMLの文法に従っていること
- id属性に設定されている値が、重複していないこと
- XMLの内容は、Mybatis Mapper XML ファイルとなっていること
- 適切なセマンティック要素を使用します: `<insert>`、`<update>`、`<delete>`、`<select>`

## Mybatis Mapper XML

- XMLのCDATAに記載されているSQLは、PostgreSQLで使用できること
- parameterType属性、resultType属性に記載されているJavaクラスが存在していること


