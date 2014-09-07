MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r
CREATE (a:Application:_Application { code : 'test', name : 'Test infra application' })
CREATE (a:Application:_Application { code : 'test1', name : 'Test infra application' })
MATCH (a:Application:_Application) WHERE a.code = 'test1' CREATE (a)-[r:HAS]->(l:Label:_Label { code : 'test.label', language : 'fr', text : 'Label test' })
CREATE (a:Application:_Application { code : 'test3', name : 'Test infra application' })
MATCH (a:Application:_Application) WHERE a.code = 'test3' CREATE (a)-[r:HAS]->(l:Label:_Label { code : 'test3.label', language : 'fr', text : 'Label test' })
CREATE (a:Application:_Application { code : 'Test', name : 'Test spring-boot, neo4j and vaadin' })
MATCH (a:Application:_Application) WHERE a.code = 'Test' CREATE (a)-[r:HAS]->(l:Label:_Label { code : 'Test.label', language : 'EN', text : 'Spring,Neo4J,Vaadin!!' })
MATCH (a:Application)-[:HAS]->(l:Label) return a,l