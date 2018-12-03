

pipeline {
    agent any
    stages {
      
      
      
        stage('Preparation') {
            steps {
              
              
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                git 'https://github.com/zhujintao/util.git'
              
              
            }
        }
      
      stage('Build') {
          steps {
                fileExists 'jenkinsfile.groovy'
                readFile 'jenkinsfile.groovy'
            
          }
      }
      
      
      
    }
}


