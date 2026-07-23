# Hands-on Lab: Branching and Merging

Objective: explain branching/merging, GitLab branch/merge requests, and
practice creating a branch, committing to it, and merging it back to master.

## Concepts

### What is a branch?

A branch is a movable pointer to a commit. `master` (or `main`/"trunk") is
just the conventional name for the default branch. Creating a new branch
(`git branch <name>`) doesn't copy any files — it adds a new pointer at the
current commit. As you commit while that branch is checked out, its pointer
moves forward while other branches stay where they were, so history can
diverge into independent lines of work and later be recombined.

### What is a merge?

Merging integrates the commits from one branch into another:

- **Fast-forward merge**: if the target branch (e.g. `master`) has no new
  commits since the source branch (e.g. a feature branch) was created, Git
  just moves the target pointer forward — no new commit is created.
- **Three-way / true merge**: if both branches have new commits (they
  "diverged"), Git creates a new **merge commit** with two parents, combining
  both histories. This is what `git merge --no-ff` forces even when a
  fast-forward would otherwise be possible, which is useful for keeping a
  visible record that a feature branch existed.

### Branch requests and merge requests in GitLab

- **Branch request** (informal term — GitLab doesn't require special
  permission to create a branch by default, but larger projects sometimes
  configure *protected branches* and require a maintainer to create or
  approve new branches). In the GitLab web UI: **Repository → Branches →
  New branch**, pick a name and a source branch/commit to branch from.
- **Merge request (MR)** is GitLab's term for what GitHub calls a "pull
  request": a request to merge one branch into another, reviewed before it
  lands. In the GitLab web UI, after pushing a branch: **Merge requests → New
  merge request**, choose the source branch (e.g. `GitNewBranch`) and target
  branch (e.g. `master`), add a description, and submit. Reviewers can
  comment, request changes, view the diff, and approve; once approved (and
  any CI pipelines pass), the **Merge** button performs the same merge this
  lab does locally, but on the server, and can optionally delete the source
  branch automatically.

## Steps performed

All demonstrated in [GitDemo/](GitDemo/), a local Git repository with an
initial commit on `master` (`README.md`).

### Branching

**1. Create a new branch**

```
$ git branch GitNewBranch
```

**2. List local and remote branches**

```
$ git branch -a
  GitNewBranch
* master
```

The `*` marks `master` as the branch currently checked out (`HEAD`). Since
this repo has no remote configured yet, only local branches are listed; once
pushed to GitLab/GitHub, `git branch -a` would additionally show entries like
`remotes/origin/master`.

**3. Switch to the branch and add a file**

```
$ git checkout GitNewBranch
Switched to branch 'GitNewBranch'

$ echo "This file was added on the GitNewBranch feature branch." > feature.txt
```

**4. Commit the changes**

```
$ git add feature.txt
$ git commit -m "Add feature.txt on GitNewBranch"
[GitNewBranch 3dd1dda] Add feature.txt on GitNewBranch
 1 file changed, 1 insertion(+)
 create mode 100644 feature.txt
```

**5. Check status**

```
$ git status
On branch GitNewBranch
nothing to commit, working tree clean
```

To make the branches genuinely diverge (so the later merge produces a real
merge commit instead of a trivial fast-forward), a second commit was added
directly on `master` in the meantime:

```
$ git checkout master
$ echo "This line was added directly on master." >> README.md
$ git add README.md
$ git commit -m "Update README on master"
```

### Merging

**1. Switch to master**

```
$ git checkout master
Switched to branch 'master'
```

**2. Command-line differences between trunk and branch**

```
$ git diff master GitNewBranch
diff --git a/README.md b/README.md
index 148d047..ad8ce38 100644
--- a/README.md
+++ b/README.md
@@ -1,4 +1,3 @@
 # GitDemo
 
 Sample project for the "Branching and Merging" hands-on lab (Week 7).
-This line was added directly on master.
diff --git a/feature.txt b/feature.txt
new file mode 100644
index 0000000..c417d40
--- /dev/null
+++ b/feature.txt
@@ -0,0 +1 @@
+This file was added on the GitNewBranch feature branch.

$ git diff master GitNewBranch --stat
 README.md   | 1 -
 feature.txt | 1 +
 2 files changed, 1 insertion(+), 1 deletion(-)
```

This shows: `GitNewBranch` is missing the README line added on `master`
(it branched off before that commit), and it adds `feature.txt` that
`master` doesn't have.

**3. Visual differences with P4Merge**

P4Merge is **not installed** on this machine, so the visual-diff step
couldn't be run here. On a machine with P4Merge installed, wire it up as
Git's diff/merge tool and launch it like this:

```
git config --global diff.tool p4merge
git config --global difftool.p4merge.cmd \
  '"C:/Program Files/Perforce/p4merge.exe" "$LOCAL" "$REMOTE"'
git config --global merge.tool p4merge
git config --global mergetool.p4merge.cmd \
  '"C:/Program Files/Perforce/p4merge.exe" "$BASE" "$LOCAL" "$REMOTE" "$MERGED"'

# Then, to see the visual diff used in this step:
git difftool master GitNewBranch
```

This opens the P4Merge GUI with the two branch versions of each changed file
side-by-side, highlighting the differing lines in color instead of the `+`/`-`
text format `git diff` prints.

**4. Merge the branch into trunk**

```
$ git merge --no-ff GitNewBranch -m "Merge branch 'GitNewBranch' into master"
Merge made by the 'ort' strategy.
 feature.txt | 1 +
 1 file changed, 1 insertion(+)

$ git status
On branch master
nothing to commit, working tree clean
```

`--no-ff` was used deliberately: because the branches had diverged, a plain
`git merge` would already create a merge commit here — `--no-ff` guarantees
one even in the simpler case where a fast-forward would otherwise apply, so
the branch's existence stays visible in history.

**5. Observe the log graph**

```
$ git log --oneline --graph --decorate --all
*   50b013f (HEAD -> master) Merge branch 'GitNewBranch' into master
|\
| * 3dd1dda (GitNewBranch) Add feature.txt on GitNewBranch
* | 2a9ed18 Update README on master
|/
* a904ed4 Initial commit on master
```

The graph clearly shows the two diverging lines of work (the direct `master`
commit and the `GitNewBranch` commit) joining back together at the merge
commit `50b013f`.

**6. Delete the branch and observe status**

```
$ git branch -d GitNewBranch
Deleted branch GitNewBranch (was 3dd1dda).

$ git branch -a
* master

$ git status
On branch master
nothing to commit, working tree clean
```

`git branch -d` (safe delete) succeeded without needing `-D` (force) because
Git could confirm all of `GitNewBranch`'s commits were already reachable from
`master` after the merge. `feature.txt` remains present on disk and in the
`master` history even though the branch pointer itself is gone — deleting a
branch only removes the pointer, not the commits it already contributed.

## Pushing to GitLab / creating the merge request there

Once a remote `GitDemo` project exists (per the prerequisites):

```
git remote add origin <your-GitDemo-repo-url>
git push -u origin master
git push -u origin GitNewBranch   # push before merging, to open an MR
```

Then, instead of merging locally, you would open a **merge request** in the
GitLab web UI (source: `GitNewBranch`, target: `master`), review the diff
there (equivalent to steps 2–3 above but rendered in the browser), and click
**Merge** — GitLab performs the same merge server-side and can delete the
source branch automatically, mirroring step 6.
