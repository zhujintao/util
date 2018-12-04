String deploy
def call(String deployname) {
    deploy = deployname
    
    this.doSQA()
    this.doBuild()
    this.doAutotest()

}

def doSQA(){
    
    stage("SonarQube analysis")
    try {
         withSonarQubeEnv("SonarQube Server") {
           sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
         }
    } catch (exc) {
	 echo "SonarQube analysis FAILURE"
    } 
}


def doBuild(){
    stage("Build")

    withMaven(maven: 'maven') {
    
      sh "mvn clean package -U"

    }

    this.doDeploy("autotest")
    this.doCheckDep("autotest")

}

def doCheckDep(String tenv){
    stage("Verify "+ tenv)
    sleep 30
    
}

def doAutotest() {
    stage("AutoTest")
    
    def remote = [:]
    remote.name = 'host-autotest'
    remote.host = '192.168.0.67'
    remote.allowAnyHosts = true
    //remote.keepAliveSec = 360
    //remote.timeoutSec = 360
    sh 'ssh root@192.168.0.67 "export JAVA_HOME=/usr/java/jdk1.8.0_172;"'
//    withCredentials([usernamePassword(credentialsId: 'testjk', passwordVariable: 'password', usernameVariable: 'userName')]) {
//      remote.user = userName
//      remote.password = password
//      try { 

//           sshCommand remote: remote, command: 'cd /data/jiatui_api_test; sh -x run.sh' 
           //sshCommand remote: remote, command: 'export PYTHONIOENCODING=UTF-8;cd /data/jiatui_api_test; sh -x run.sh jiajiao note'


 			
//      } catch (exc) {
//           currentBuild.result = 'FAILURE'
//           echo currentBuild.result
//     }
     this.doDeployfrom("test from autotest")
     this.doCheckDep("test")

//}


}

def doDeployfrom(String tfenv){

    stage("Deploy to " + tfenv)

} 

def doDeploy(String toenv){

    stage("Deploy to " + toenv)
    echo "${env.REGISTRY_ARCH}/" + deploy + ":${env.KUBE_ARCH_NS}_b${env.Build_id}"
    sh 'curl -o login-service-restapi/target/Dockerfile ${HTTP_FILE_URL}/Dockerfile.v1'
    sh  "ls -l login-service-restapi/target"
    docker.withRegistry(env.REGISTRY_ARCH,'Harbor') {
      def buildImage = docker.build("architect/" + deploy + ":${env.KUBE_ARCH_NS}_b${env.Build_id}","login-service-restapi/target")
      buildImage.push()
    }


}


return this;

