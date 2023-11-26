def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
} 

def buildImage() {
    echo 'Building the docker image ...'
    withCredentials([usernamePassword(credentialsId:'docker-hub-repo', passwordVariable:'PASSWORD', usernameVariable: 'USERNAME')]) {
                sh """
                    docker build -t opeyemiagbadero/demo-app:jma-2.0 .
                    echo \${PASSWORD} | docker login -u \${USERNAME} --password-stdin
                    docker push opeyemiagbadero/demo-app:jma-2.0
                """
            }
}

def deployApp() {
    echo 'Deploy the application...'
    }

return this