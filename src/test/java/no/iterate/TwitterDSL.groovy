package no.iterate

import twitter4j.TwitterFactory

def twitter = TwitterFactory.getSingleton()
String[] userNames = ["swift_andy","kentbeck","tomjohannesbang"];
twitter.lookupUsers(userNames).each { it -> println "@$it.screenName> $it.status.text"}