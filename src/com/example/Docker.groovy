#!/usr/bin/env groovy
package com.example;

import java.io.Serializable;

class Docker implements Serializable {
    def script
    Docker(script){
        this.script = script
    }

    def buildDockerImage(String imageName){
        script.echo "building the docker image..."
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh 'docker build -t krisadaporn/my-repo:jma-2.0 .'
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
            script.sh 'docker push krisadaporn/my-repo:jma-2.0'
        }
    }
}
