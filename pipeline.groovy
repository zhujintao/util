def call() {
  
    this.doBuild()
  
}


def doBuild(){
    stage("Build")
    echo "doBuild"
    echo env.BUILD_ID

}


return this;
