String deploy
def call(String deploy) {
    deploy = deploy
    this.doBuild()


}


def doBuild(){
    stage("Build")
    echo "doBuild"
    echo env.BUILD_ID
    this.doDeploy("autotest")

}

def doDeploy(String toenv){

    stage("Deploy to " + toenv)
    echo "${env.REGISTRY_ARCH}/" + deploy + ":${env.KUBE_ARCH_NS}_b${env.Build_id}"

}


return this;
