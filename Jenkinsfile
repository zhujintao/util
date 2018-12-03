node {
    
    git 'https://github.com/zhujintao/util.git'
    sh 'curl -o pipeline.groovy ${HTTP_FILE_SERVER}/Jenkinsfile.v1'
    
    def pipeline = load 'pipeline.groovy'
    pipeline("loginserver")

}


