challenges
==========

This repo is a work in progress. For the moment it'll be a compendium of challenges suggested to all scala developers. this is being managed and maintaind by [scala dev bcn](http://www.meetup.com/Scala-Developers-Barcelona/).

## [Microchallenges](./micro/)

Small challenges proposed on a weekly basis to learn scala and their basic concepts. These must be able to be solved without too much trouble and should let to compare different approaches and learn from each other.

## [Monthly challenges](./monthly/)

More demanding challenges aimed to create pet (or even real) applications using state-of-the-art frameworks or technologies. First ones could be done individually and more complex ones by small teams. Each month a new challenge is going to be proposed and afterwards the experience shared in one (or more) meet ups. 

## APPENDIX

We're using a Google Group (https://groups.google.com/forum/?hl=en#!forum/scala-developers-barcelona) separate from the meetup mailing lists as a dedicated communication channel for challenges. This way we can share experiences, ask for help and raise questions without messing with the meetup list. 

Additionally to the challenges we may have,

* Announcements in meetup main list
* Meetups & dojos

So stop by [scala dev bcn](http://www.meetup.com/Scala-Developers-Barcelona/) and join!

### How to set up the local repository

We intend to work with forked repositories and pull-requests to this one. If you want to have the upcoming changes from this repository you just need to clone this one and set up the push url to your fork.

<pre>
D:\sources>git clone git@github.com:scala-developers-bcn/challenges.git
Cloning into 'challenges'...
remote: Counting objects: 1359, done.
remote: Compressing objects: 100% (583/583), done.
remote: Total 1359 (delta 370), reused 1274 (delta 285)R
1210/1359), 156.00 KiB | 131 KiB/s
Receiving objects: 100% (1359/1359), 170.77 KiB | 131 KiB/s, done.
Resolving deltas: 100% (370/370), done.

D:\sources\challenges>git remote show origin
* remote origin
  Fetch URL: git@github.com:scala-developers-bcn/challenges.git
  Push  URL: git@github.com:scala-developers-bcn/challenges.git
  HEAD branch: master
  Remote branch:
    master tracked
  Local branch configured for 'git pull':
    master merges with remote master
  Local ref configured for 'git push':
    master pushes to master (up to date)

D:\sources\challenges>git remote set-url --push origin git@github.com:akustik/challenges.git

D:\sources\challenges>git remote show origin
* remote origin
  Fetch URL: git@github.com:scala-developers-bcn/challenges.git
  Push  URL: git@github.com:akustik/challenges.git
  HEAD branch: master
  Remote branch:
    master tracked
  Local branch configured for 'git pull':
    master merges with remote master
  Local ref configured for 'git push':
    master pushes to master (up to date)

D:\sources\challenges>git push
Counting objects: 493, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (182/182), done.
Writing objects: 100% (422/422), 54.83 KiB, done.
Total 422 (delta 113), reused 412 (delta 107)
To git@github.com:akustik/challenges.git
   e4d2fa5..2282536  master -> master
</pre>
