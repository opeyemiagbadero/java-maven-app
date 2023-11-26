def buildApp() {
    echo 'building the application...'
} 

def testApp() {
    echo 'test the application...'
}

def deployApp() {
    echo 'deploy the application...'
    echo "Deploying version ${params.VERSION}"
}

return this