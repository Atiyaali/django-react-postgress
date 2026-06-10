def build(frontimage , backimage , nginximage , frontpath , backpath , nginxpath){
 sh "docker build -f ${frontpath} -t ${frontimage} ."
  sh "docker build -f ${backpath} -t ${backimage} ."
   sh "docker build -f ${nginxpath} -t ${nginximage} ."
}

def push(frontimage , backimage , nginximage){
    withCredentials([
 usernamePassword(  credentialsId: "dockerhub_creds" , usernameVariable: 'USERNAME' , passwordVariable: 'PASS')
  
    ]){
        sh "echo ${PASS} | docker login -u ${USERNAME} --password-stdin"
    }

sh "docker push ${frontimage}"
sh "docker  push ${backimage}"
sh "docker push ${nginximage}"
}
def deploy(){
    echo "i am deploying"
}
return this