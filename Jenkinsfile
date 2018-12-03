node {
    
    //git 'https://github.com/zhujintao/util.git'
    git 'https://github.com/zhujintao/simple-java-maven-app.git'
    sh 'curl -o pipeline.groovy ${HTTP_FILE_URL}/Jenkinsfile.v1'
    
    def pipeline = load 'pipeline.groovy'
    pipeline("loginserver")

}
