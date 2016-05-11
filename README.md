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
Or create a new branch (your new branch is based on the last branch you where in, so if you want to start in unstable , then checkout unstable with the previous syntax)
```
git checkout -b <new branch name> origin/<new branch name>
```
If you made a new branch and didn't set the upstream at the same time you need to set an upstream with
```
git branch -u origin/<new branch name>
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

Now that you have pushed up your code, you may need to [merge](https://git-scm.com/docs/git-merge) with others work
 ```
git fetch --all
git merge origin/unstable
<Fix merge conflicts>
git add <files that you fixed>
git commit -m "Merging in Unstable"
git push
```
If there are no merge conflicts then you can just do a push

Once you feature is ready to merge to Unstable you go to GitHub and make a [pull request](https://help.github.com/articles/using-pull-requests/) from your branch to Unstable. You need two thumbs up before your code is ready to be merged. Someone other than you should merge it.

For More Help visit
- [Cheat Sheet](https://gist.github.com/iansheridan/870778)
- [Index of git commands](https://git-scm.com/book/commands)
- [Reference](https://git-scm.com/docs)


If all else fails, delete your repo and start again.
