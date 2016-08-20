Vagrant.configure("2") do |config|
 config.vm.define "server" do |server|
  server.vm.provider "docker" do |d|
   d.name = "mysql"
   d.build_dir = "docker/mysql"
   d.ports = ["3306:3306"]
   d.vagrant_vagrantfile = "./docker/host/Vagrantfile"
  end
 end
end