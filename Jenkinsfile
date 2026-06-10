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
                gv.build("atiyadocker/django_react_front:${env.VERSION}" , "atiyadocker/django_react_back:${env.VERSION}","atiyadocker/django_react_nginx:${env.VERSION}", "apps/docker/frontend/Dockerfile" , "apps/docker/backend/Dockerfile" , "apps/docker/nginx/Dockerfile" )
                }
              
            }
        }
        stage("push"){
            steps{
                script{
                   gv.push("atiyadocker/django_react_front:${env.VERSION}" , "atiyadocker/django_react_back:${env.VERSION}","atiyadocker/django_react_nginx:${env.VERSION}" )
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