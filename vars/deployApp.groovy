def call(String containerName, String port, String imageName) {
    sh """
        docker stop ${containerName} || true
        docker rm ${containerName} || true
        docker run -d --name ${containerName} -p ${port}:${port} ${imageName}:latest
    """
}

