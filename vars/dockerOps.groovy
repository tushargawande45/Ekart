def buildAndPush(String imageName, String dockerfilePath) {
    sh """
        docker login -u tushargawande --password Tushar@123
        docker build -t ${imageName} -f ${dockerfilePath} .
        docker tag ${imageName} tushargawande/${imageName}:latest
        docker push tushargawande/${imageName}:latest
    """
}

