#!/bin/bash
# This is set to automatically run on first boot
echo "Starting FRC1018 Raspberry Pi Setup Program..."
echo "Updating..."
sudo apt-get update
echo "Installing necessary packages"
sudo apt-get install -y oracle-java8-jdk fs-webcam
# Enable ssh
echo "Enabling SSH..."
update-rc.d ssh enable && invoke-rc.d ssh start
echo "Installing content directories..."
# Make the vision folder
mkdir /home/pi/vision
echo "Generating startup scripts..."
# Write an empty script for the service to run
echo "#!/bin/bash
echo \"No code to run. Use ./gradlew deploy to upload the necessary files\"
" >> /home/pi/runVisionSystem
# Write the service descriptor file
echo "[Unit]
Description=FRC Team 1018 Raspberry Pi Vision System
After=network.target

[Service]
ExecStart=/home/pi/vision/runVisionSystem
User=pi

[Install]
WantedBy=multi-user.target
" >> /etc/systemd/system/vision.service
# Enable the service
sudo systemctl enable vision.service
echo "Setting IP to 10.10.18.3..."
sudo echo "

interface eth0

static ip_address=10.10.18.3/8
static routers=10.10.18.1
static domain_name_servers=10.10.18.1
" >> /etc/dhcpcd.conf
echo "Cleaning up..."
# PiBakery will handle the rest
