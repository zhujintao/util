String deploy
def call(String deployname) {
    deploy = deployname

    this.doBuild()
    this.doAutotest()

}

def doBuild(){
    stage("Build")
    echo "doBuild"
    echo env.BUILD_ID
    this.doDeploy("autotest host")
}

def doAutotest() {
    stage("AutoTest")

    def remote = [:]
    remote.name = 'host-autotest'
    remote.host = '192.168.1.5'
    remote.allowAnyHosts = true
    withCredentials([usernamePassword(credentialsId: 'taomac', passwordVariable: 'password', usernameVariable: 'userName')]) {
      echo password
      echo userName
      remote.user = userName
      remote.password = password
      try {
      sshCommand remote: remote, command: "ls -lrt"
      sshCommand remote: remote, command: "exit -1"
      } catch (exc) {
      echo currentBuild.result
     }

}

}

def doDeploy(String toenv){

    stage("Deploy to " + toenv)
    echo "${env.REGISTRY_ARCH}/" + deploy + ":${env.KUBE_ARCH_NS}_b${env.Build_id}"

}
