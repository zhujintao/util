String deploy
def call(String deployname) {
    deploy = deployname
    this.doBuild()


}


def doBuild(){
    stage("Build")
    echo "doBuild"
    echo env.BUILD_ID
    this.doDeploy("autotest")

}

def goAutoTest() {
    stage("AutoTest")
    

}





def doDeploy(String toenv){

    stage("Deploy to " + toenv)
    echo "${env.REGISTRY_ARCH}/" + deploy + ":${env.KUBE_ARCH_NS}_b${env.Build_id}"

}


return this;
