# Don’t get stung by OWASP
## An intro into writing code for greater Android Security

This is the companion app to my ["Don’t get stung by OWASP"](https://ed-george.github.io) talk, in which I discuss the Top 5 rated security risks to Mobile as determined by the OWASP Foundation

### My Secure App ™️

* This _very_ basic app has two activities `LoginActivity` and `MainActivity`
*  The user enters the PIN and is taken to the `MainActivity`

* PIN is initially 1234 and in later branches 123456
* Later branches call the [Rick & Morty API](https://rickandmortyapi.com/) for a custom greeting

### How to use this repo

Each of the Top 5 vulnerbilities are demonstrated (in some form) within this very basic application.

![](https://i.imgur.com/ls12PGb.png)

For each vulnerbility, a branch with examples of the vulnerbilities is available. Additionally, each of these branches has an associated 'fix' branch that shows how to address the vulnerbilitiy. 

Please use the git history of this repo to help guide you

### Further Reading

Please checkout these excellent resources:

* [OWASP Mobile Top 10](https://owasp.org/www-project-mobile-top-10/)
* [HackTricks](https://book.hacktricks.xyz/)
* [spght.dev/talks](https://ed-george.github.io/talks/)

### Disclaimer

This repo is NOT associated with and/or endorsed by the OWASP Foundation or my employer!
