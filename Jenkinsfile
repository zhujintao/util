node {

  stage('Checkout') {
    echo "${BRANCH_NAME} ${env.BRANCH_NAME}"
    scm Checkout

  }

  stage('Build-all-targets-in-parallel'){

    def workspace = pwd()
    echo workspace
    parallel(
      'first-parallel-target' :
       {
         // Load the file 'file1.groovy' from the current directory, into a variable called "externalMethod".
         //callScriptOne()
         def externalMethod = load("file1.groovy")
         // Call the method we defined in file1.
          externalMethod.firstTest()
       },
       'second-parallel-target' :
      {
         //callScriptTwo()
         def externalMethod = load("file2.groovy")
         // Call the method we defined in file1.
         externalMethod.testTwo()
        }
    )
  }
  stage('Cleanup workspace'){
    deleteDir()
  }
}
