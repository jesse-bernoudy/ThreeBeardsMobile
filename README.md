# ThreeBeardsMobile

#### Git Hints

Launch git bash and change into your repo director

Clone the repo
```
git clone https:jesse-bernoudy/ThreeBeardsMobile.git
```
Get all the branches
```
git fetch --all
```
You can view your branches with
```
git branch --all
```
Either checkout an existing branch with
```
git checkout <branch name>
```
Or create a new branch
```
git checkout -b <new branch name> origin/<new branch name>
```
If you made a new branch you need to set an upstream
```
git git branch -u origin/<new branch name>
```
Do your work. When ready to make a commit start with
```
git status
```
Add the files you actually touched and want to commit
```
git add <file name>
```
Do git status again to make sure you have the right files. if you have extra files then you can clear them out one by one with
```
git checkout <filename>
```
Or clear your whole list with
```
git reset HEAD
```
Once you have the correct files ready to commit do so with
```
git commit -m "My commit message"
```
It's OK to make lots of commits
After you have finsihed your work for the day make sure to push your changes
```
git push
```
If prompted for a username and password you use your GitHub credentials
