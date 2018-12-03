pipeline {
    agent any
    stages {
        stage('Preparation') {
            steps {
            
                    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                    git 'https://github.com/zhujintao/util.git'

                    sh 'curl -o jenkinsfile.groovy http://192.168.0.142:8089/files/Jenkinsfile.v1'
              
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


