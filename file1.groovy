def firstTest() {
    // stuff here
}

def testTwo() {
    //more stuff here
}
...

return [
    firstTest: this.&firstTest,
    testTwo: this.&testTwo
]
