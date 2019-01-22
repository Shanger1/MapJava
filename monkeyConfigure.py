# Imports the monkeyrunner modules used by this program
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

# Connects to the current device, returning a MonkeyDevice object
device = MonkeyRunner.waitForConnection()

# Installs the Android package. Notice that this method returns a boolean, so you can test
# to see if the installation worked.
device.installPackage('/Users/miau/MapJava/app/build/outputs/apk/debug/app-debug.apk')

# sets a variable with the package's internal name
package = 'com.example.miau.googlemap'

# sets a variable with the name of an Activity in the package
activity = 'com.example.miau.googlemap.MainActivity'

# sets the name of the component to start
runComponent = package + '/' + activity

# Runs the component
device.startActivity(component=runComponent)
MonkeyRunner.sleep(3)

# Takes a screenshot
result = device.takeSnapshot()

# Writes the screenshot to a file
result.writeToFile('/Users/miau/MapJava/shot1.png','png')

# sets a variable with the name of an Activity in the package
activity = 'com.example.miau.googlemap.MapFragment'

# sets the name of the component to start
runComponent = package + '/' + activity

# Runs the component
device.startActivity(component=runComponent)
MonkeyRunner.sleep(3)

# Takes a screenshot
result = device.takeSnapshot()

# Writes the screenshot to a file
result.writeToFile('/Users/miau/MapJava/shot1.png','png')