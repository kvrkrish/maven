# Tomcat installatin : 
sudo yum install tomcat tomcat-webapps tomcat-admin-webapps tomcat-docs-webapp tomcat-javadoc -y

**# java-tomcat-maven-example
Prerequisites:**

Edit /usr/share/tomcat/conf/tomcat-users.xml/tomcat-users.xml in tomcat ::

	<role rolename="tomcat"/>
	<role rolename="admin-script"/>
	<role rolename="manager-script"/>
	<role rolename="manager-gui"/>
	<role rolename="manager-jmx"/>
	<role rolename="manager-status"/>
	<role rolename="manager"/>
	<role rolename="admin"/>
	<user password="password" roles="tomcat" username="admin"/>
	<user password="password" roles="manager-gui" username="admin"/>
	<user password="password" roles="admin,admin-script,manager-gui,manager-script,manager-jmx,manager-status" username="admin"/>

Edit /etc/maven/settings.xml in maven ::

	<server>
	<id>TomcatServer</id>
	<username>admin</username>
	<password>password</password>
	</server>
	<server>     
	<id>nexus-site</id>
	<username>admin</username>
	<password>dba1e994-b90d-498f-88fd-7b9ee8291627</password>
	</server>

---------------------------------
**Automation:**
Deploying Project War files to Tomcat using Maven ::

	mvn install tomcat7:deploy

--------------------------------
**Automation:**

NEXUS INSTALLATION ::
-------------------

Deploying Project snapshots to nexus using Maven ::

Automation: Deploying Project snapshots to nexus using Maven ::

install nexus

yum install -y maven java-1.8.0-openjdk

mkdir /opt/nexus

cd /opt/nexus

wget http://www.sonatype.org/downloads/nexus-latest-bundle.tar.gz

tar -xzvf nexus-latest-bundle.tar.gz && version=$(ls | grep -v latest | grep 'nexus-' | cut -f'2,3' -d'-') && ln -s nexus-$version nexus

cp /opt/nexus/nexus/bin/nexus /etc/init.d/

sed -i.bak -e 's/NEXUS_HOME=\"..\"/NEXUS_HOME=\"\/opt\/nexus\/nexus\"/' /etc/init.d/nexus

sed -i.bak -e 's|nexus-webapp-context-path=/nexus|nexus-webapp-context-path=/|' /opt/nexus/nexus/conf/nexus.properties

useradd nexus

chown -R nexus:nexus /opt/nexus/ && sudo chown nexus:nexus /etc/init.d/nexus

sed -i.bak -e 's/#RUN_AS_USER=/RUN_AS_USER=nexus/' /etc/init.d/nexus

systemctl stop firewalld && sudo systemctl disable firewalld

echo 'RUN_AS_USER=nexus' >> /etc/environment && export RUN_AS_USER=nexus

systemctl daemon-reload

/etc/init.d/nexus start && chkconfig nexus on


	mvn clean deploy
