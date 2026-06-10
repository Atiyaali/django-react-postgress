// @Library("jenkins_shared_library") _
def gv
pipeline{
    agent any
  environment {
        DEPLOY_ENV = 'production'
    }
    stages{
        stage(init){
            steps{
                script{
                  gv = load 'script.groovy'
                }
            }
        }
        stage("version"){
             steps{
                script{
                   if(DEPLOY_ENV  == "production"){
                    sh 'git fetch --tags'
                      env.VERSION = sh(
                        script: 'git describe --tags',
                        returnStdout: true
                      ).trim()

                   }
                   else{
                      env.VERSION = env.BUILD_NUMBER
                   }
                }

             }
        }
        stage("build"){
            steps{
                script{
                gv.build("atiyadocker/DJANGO_REACT_FRONT:${env.VERSION}" , "atiyadocker/DJANGO_REACT_BACK:${env.VERSION}","atiyadocker/DJANGO_REACT_NGINX:${env.VERSION}", "apps/docker/frontend/Dockerfile" , "apps/docker/backend/Dockerfile" , "apps/docker/nginx/Dockerfile" )
                }
              
            }
        }
        stage("push"){
            steps{
                script{
                   gv.push("atiyadocker/DJANGO_REACT_FRONT:${env.VERSION}" , "atiyadocker/DJANGO_REACT_BACK:${env.VERSION}","atiyadocker/DJANGO_REACT_NGINX:${env.VERSION}" )
                }
            
            }
        }
        stage("deploy"){
            steps{
                script{
                 gv.deploy()
                }
            }
        }
    }
}