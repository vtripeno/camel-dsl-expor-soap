# Camel DSL for a REST API - Demo
- Este é um projeto Camel que utiliza DSL para formar suas rotas;
- Para criar um projeto deste tipo selecione File > New > Fuse Integration Project;

## Breve Descrição
- Este projeto tem como objetivo demonstrar uma implementação com Camel DSL para expor um web service;
	
## Configurações Fuse
JBossFuse:karaf@root> features:install camel-jetty
JBossFuse:karaf@root> install -s mvn:org.apache.servicemix.bundle/org.apache.servicemix.bundle.saaj.imp/1.3.21_1 --> Mais informações sobre esta instalação na seção de Detalhes;

## Build
- Execução com Teste Unitário
	- mvn clean install
- Execução sem o Teste Unitário
	- mvn clean install -Dmaven.test.skip=true
-Execução somente do teste
	- mvn clean test

## Deploy
Por um aterfato do Maven
JBossFuse:karaf@root> osgi:install -s mvn:com.mywebservice/camel-blueprint-web-service/1.0.0-SNAPSHOT
Output Console --> Bundle ID: <ID>

Pelo arquivo direto
JBossFuse:karaf@root> osgi:install -s file:///home/user/dev/camel-consumes-rest/target/camel-blueprint-web-service-1.0.0-SNAPSHOT.jar
Output Console --> Bundle ID: <ID>

## Undeploy
JBossFuse:karaf@root> osgi:uninstall <ID>

## Detalhes
- Para conseguir visualizar o contrato wsdl, é necessário acessar o endereço http://localhost:9001/MyWebService?wsdl;
- Para fazer a extração de um wsdl para dentro do projeto (fazer o wsdl se tornar uma estrutura de classes), é necessário rodar o comando mvn generate-sources;
- Quando se trata de um consumo de SOAP, o servidor JBOSS possui um pequeno problema, pois o mesmo não possui as dependências necessárias para a execução de criação de mensagens SOAP, retornando o seguinte erro;
	javax.xml.soap.SOAPException: Unable to create MessageFactory: Provider for javax.xml.soap.MessageFactory cannot be found;
- Ao procurar sobre o seguinte erro, foi possível encontrar no próprio site de respostas da Red Hat a seguinte resolução: 
		JBossFuse:karaf@root> install -s mvn:org.apache.servicemix.bundle/org.apache.servicemix.bundle.saaj.imp/1.3.21_1;
