FROM centos:7

ENV NODE_HOME=/home/depedencies/node-v12.14.1-linux-x64

ENV MAVEN_HOME=/home/depedencies/apache-maven-3.6.3

ENV JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.232.b09-0.el7_7.x86_64

ENV PATH="$NODE_HOME/bin:$MAVEN_HOME/bin:$PATH"

RUN cd home && mkdir depedencies && mkdir project

RUN yum -y install wget

RUN yum -y install java-1.8.0-openjdk-devel

RUN javac -version && java -version

RUN wget "https://nodejs.org/dist/v12.14.1/node-v12.14.1-linux-x64.tar.xz" && tar -xvf node-v12.14.1-linux-x64.tar.xz -C /home/depedencies && cd /home/depedencies && ls

#RUN ln -s $NODE_HOME/bin/npm /usr/local/bin && ln -s $NODE_HOME/bin/node /usr/local/bin

RUN npm install -g @angular/cli

#RUN ln -s $NODE_HOME/bin/ng /usr/local/bin

RUN cd /home && wget "https://www-eu.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz" && tar -xvf apache-maven-3.6.3-bin.tar.gz -C /home/depedencies

#RUN ln -d $MAVEN_HOME/bin/mvn /usr/local/bin

RUN yum -y install git

RUN cd /home/project && git clone https://github.com/welsonlimawlsn/estoque.git && cd /home/project/estoque && mvn clean install

RUN cd /home && wget "https://download.jboss.org/wildfly/17.0.1.Final/wildfly-17.0.1.Final.tar.gz" && tar -xvf wildfly-17.0.1.Final.tar.gz -C /home/depedencies
