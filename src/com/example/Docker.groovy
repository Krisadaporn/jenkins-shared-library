#!/usr/bin/env groovy
package com.example;

import java.io.Serializable;

class Docker implements Serializable {
    def script
    Docker(script){
        this.script = script
    }

    def buildImage(String imageName){
        script.echo "building the docker image..."
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh 'docker build -t krisadaporn/demo-app:java-maven-1.0 .'
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
            script.sh 'docker push krisadaporn/demo-app:java-maven-1.0'
        }
    }
}
