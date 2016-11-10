node {
    stage "Checkout"
    git url: "https://github.com/constantijn/devoxx-guestbook.git"

    stage "Build vars"
    def dockerImageName = "eu.gcr.io/next-amsterdam/devoxx-guestbook"
    def gitRevision = shOut "git rev-parse --short HEAD"
    def dockerImageTag = "${currentBuild.number}-${gitRevision}"
    def dockerImage = "${dockerImageName}:${dockerImageTag}"
    def deploymentFile = "devoxx-guestbook-deployment.yml"

    stage 'Maven build'
    sh "mvn clean package"

    stage "Docker build"
    sh "docker build -t ${dockerImage} ."

    stage "Docker push"
    sh "gcloud docker push ${dockerImage}"

    stage "Kubernetes deploy"
    // sh "export DOCKER_IMAGE_TAG=${dockerImageTag}\n" +
    //   "envsubst < .k8s/${deploymentFile}.template > ${deploymentFile}"
    // sh "kubectl apply -f ${deploymentFile}"
    sh "kubectl set image deployment/devoxx-guestbook devoxx-guestbook=${dockerImage}"
    sh "kubectl rollout status deployment/devoxx-guestbook"
}

def shOut(script) {
    sh "${script} > .script-output"
    readFile(".script-output").trim()
}
