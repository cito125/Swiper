# Swiper

![Alt text](https://github.com/cito125/Swiper/blob/master/app/src/main/res/drawable/swiprlogooutline.png)

## Description

Swiper is an app that connects users leaving train stations to users who would like a swipe to enter the train station. 

##TODO

App is currently quite inefficient. There are two types of users: Donors and recipients. 
- Donor: Gives swipes
- Recipient: Recieves swipes

When a donor is by a station they are prompted by a notification if they are headed somewhere. Upon clicking on the 
notification (Should also implement an option for them to say no but, meh I'm young, jk will do), they go to an activity where
they can input there destination. Their role in the process is then complete. 

The inefficiency comes from me doing all the work of finding the station they are leaving from on their phone and then sending firebase the message and user group to notify. I feel as though that should be done on a server so that is something I am working on.

It also doesn't work yet so, there's that.

## Dream Team Collaborators

- [Danny Lui](https://github.com/dannylui91)
  * Managed Users in Firebase
  * Collaborated with [Andres](https://github.com/cito125) to use Firebase notifications
- [Rusi Li](https://github.com/rusili)
  * In charge of all the beautiful UI i.e. if it isn't beautiful, Rusi didn't do it

