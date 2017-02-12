# Swiper

![Alt text](https://github.com/cito125/Swiper/blob/master/app/src/main/res/drawable/swiprlogooutline.png)

## Description

Swiper is an app that connects users leaving train stations to users who would like a swipe to enter the train station. 

## WORKS LIKE

 There are two types of users: Donors and recipients. 
- Donor: Gives swipes
- Recipient: Recieves swipes

When a donor is by a station, they are sent a notification prompting them where they are headed. Upon clicking on the 
notification they go to an activity where they can input there destination. Their role in the process is then complete.

All recipients within a certain radius of the donor's final station are then notified of the station and ETA(Estimated Time of Arrival) of a donor.

## TODO

- Implement Firebase notifications

- App is currently quite inefficient.

 When a donor is by a station they are prompted by a notification if they are headed somewhere.  (Should also implement an option for them to say no but, meh I'm young, jk will do). 

 The inefficiency comes from me doing all the work of finding the station they are leaving from on their phone and then sending firebase the message and user group to notify. 
 The server should do the work of finding the destination station and calculating donor ETA.

It also doesn't serve its purpose yet so, there's that. Once the app is functional, we will definitely make it look pretty.

## Dream Team Collaborators

- [Danny Lui](https://github.com/dannylui91)
  * Manages Users in Firebase
  * Collaborated with [Andres](https://github.com/cito125) to use Firebase notifications
- [Rusi Li](https://github.com/rusili)
  * In charge of all the beautiful UI i.e. if it isn't beautiful, Rusi didn't do it

