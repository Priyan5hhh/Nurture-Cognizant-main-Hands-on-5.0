# Hands-on Lab: Resolving Merge Conflicts

Objective: explain and practice resolving a merge conflict that arises when
both the trunk (`master`) and a feature branch modify the same part of the
same file.

## Why conflicts happen

Git can auto-merge two branches when their changes don't overlap. A
**conflict** happens when both branches changed the *same lines* of the
*same file* (or, as here, both independently created a new file with the
same name but different content — an "add/add" conflict). Git can't decide
which version is "correct," so it stops the merge, marks the file as
unmerged, and writes both versions into the file separated by conflict
markers (`<<<<<<<`, `=======`, `>>>>>>>`) for a human to resolve.

All steps below were run in [GitDemo/](GitDemo/), a local Git repository.

## 1. Verify master is clean

```
$ git status
On branch master
nothing to commit, working tree clean
```

## 2. Create branch "GitWork" and add hello.xml

```
$ git checkout -b GitWork
Switched to a new branch 'GitWork'

$ cat > hello.xml   # <hello><message>Hello</message></hello>
$ git status
On branch GitWork
Untracked files:
	hello.xml
```

```
$ git add hello.xml
$ git commit -m "Add initial hello.xml on GitWork"
```

## 3. Update hello.xml content and observe status

```
$ # edited hello.xml: message -> "Hello from GitWork branch", added <author>
$ git status
On branch GitWork
Changes not staged for commit:
	modified:   hello.xml
```

## 4. Commit the change to the branch

```
$ git add hello.xml
$ git commit -m "Update hello.xml content on GitWork"
[GitWork f9d4424] Update hello.xml content on GitWork
```

## 5. Switch to master

```
$ git checkout master
Switched to branch 'master'
```

## 6. Add hello.xml to master with different content

```
$ cat > hello.xml   # <hello><message>Hello from master trunk</message><owner>master-user</owner></hello>
$ git status
On branch master
Untracked files:
	hello.xml
```

Note `hello.xml` does not yet exist in `master`'s history — it only exists
on `GitWork` so far — so this creates two independent versions of a
same-named new file on each branch, the setup for an add/add conflict.

## 7. Commit the change to master

```
$ git add hello.xml
$ git commit -m "Add hello.xml on master with different content"
[master 54885b4] Add hello.xml on master with different content
```

## 8. Observe the log

```
$ git log --oneline --graph --decorate --all
* 54885b4 (HEAD -> master) Add hello.xml on master with different content
| * f9d4424 (GitWork) Update hello.xml content on GitWork
| * f75a66f Add initial hello.xml on GitWork
|/
* d8c08ca Initial commit on master
```

The two branches have clearly diverged, each with its own `hello.xml`.

## 9. Check differences with git diff

```
$ git diff master GitWork -- hello.xml
diff --git a/hello.xml b/hello.xml
index ce02b56..894e5c5 100644
--- a/hello.xml
+++ b/hello.xml
@@ -1,5 +1,5 @@
 <?xml version="1.0"?>
 <hello>
-  <message>Hello from master trunk</message>
-  <owner>master-user</owner>
+  <message>Hello from GitWork branch</message>
+  <author>branch-user</author>
 </hello>
```

## 10. Visualize with P4Merge

P4Merge is **not installed** on this machine, so this step is documented
rather than run directly (see the branching/merging lab for the
`git config diff.tool` / `difftool.p4merge.cmd` setup). Once configured:

```
git difftool master GitWork -- hello.xml
```

This opens P4Merge showing the master and branch versions of `hello.xml`
side-by-side with the differing `<message>`/`<owner>`/`<author>` lines
highlighted in color.

## 11. Merge the branch into master

```
$ git merge GitWork -m "Merge branch 'GitWork' into master"
Auto-merging hello.xml
CONFLICT (add/add): Merge conflict in hello.xml
Automatic merge failed; fix conflicts and then commit the result.
```

## 12. Observe the git status and conflict markup

```
$ git status
On branch master
You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
	both added:      hello.xml

$ cat hello.xml
<?xml version="1.0"?>
<hello>
<<<<<<< HEAD
  <message>Hello from master trunk</message>
  <owner>master-user</owner>
=======
  <message>Hello from GitWork branch</message>
  <author>branch-user</author>
>>>>>>> GitWork
</hello>
```

- `<<<<<<< HEAD ... =======` is **your current branch's** version (`master`).
- `======= ... >>>>>>> GitWork` is the **incoming branch's** version.

## 13. Resolve with a 3-way merge tool

On a machine with P4Merge configured as `merge.tool`, this step would be:

```
git mergetool
```

P4Merge opens a 3-pane view — **base** (the common ancestor before either
branch changed the file), **local/yours** (`master`), and **remote/theirs**
(`GitWork`) — with a fourth pane for the merged result, letting you pick
individual hunks from either side or hand-edit the combined output visually
instead of editing raw conflict markers in a text editor. (P4Merge isn't
installed here, so the conflict was resolved by directly editing the file
and removing the markers — functionally the same outcome a mergetool
session would produce.)

Resolved content, combining both sides' intent:

```xml
<?xml version="1.0"?>
<hello>
  <message>Hello from master trunk and GitWork branch</message>
  <owner>master-user</owner>
  <author>branch-user</author>
</hello>
```

## 14. Commit the resolved changes to master

```
$ git add hello.xml
$ git status
All conflicts fixed but you are still merging.
	modified:   hello.xml

$ git commit -m "Resolve merge conflict between master and GitWork in hello.xml"
[master 5d809bd] Resolve merge conflict between master and GitWork in hello.xml
```

Because a merge commit was already started, `git commit` with no `-m`
would also work and would pre-fill the editor with a default
"Merge branch 'GitWork'..." message — an explicit `-m` was used here instead.

## 15. Observe status and add the merge tool's backup file to .gitignore

Merge tools (including P4Merge, via `git mergetool`) leave a `<file>.orig`
backup of the pre-resolution, conflict-marked version next to the resolved
file, so you can compare or recover if the resolution went wrong. Git
doesn't clean these up automatically:

```
$ git status
On branch master
Untracked files:
	hello.xml.orig
```

Added to `.gitignore`:

```
# Backup files left behind by merge tools (e.g. P4Merge / git mergetool) after resolving conflicts
*.orig
```

```
$ git status
On branch master
Untracked files:
	.gitignore
```

`hello.xml.orig` no longer appears — only the new `.gitignore` itself is
untracked now.

## 16. Commit the changes to .gitignore

```
$ git add .gitignore
$ git commit -m "Ignore .orig backup files left by merge tools"
[master c4a3a69] Ignore .orig backup files left by merge tools

$ git status
On branch master
nothing to commit, working tree clean
```

## 17. List all available branches

```
$ git branch -a
  GitWork
* master
```

## 18. Delete the branch that was merged into master

```
$ git branch -d GitWork
Deleted branch GitWork (was f9d4424).

$ git branch -a
* master
```

`-d` (safe delete) succeeds without needing `-D` because every commit on
`GitWork` is reachable from `master` after the merge — Git can confirm
nothing would be lost.

## 19. Observe the final log

```
$ git log --oneline --graph --decorate
* c4a3a69 (HEAD -> master) Ignore .orig backup files left by merge tools
*   5d809bd Resolve merge conflict between master and GitWork in hello.xml
|\
| * f9d4424 Update hello.xml content on GitWork
| * f75a66f Add initial hello.xml on GitWork
* | 54885b4 Add hello.xml on master with different content
|/
* d8c08ca Initial commit on master
```

Even though the `GitWork` branch pointer is deleted, its commits
(`f75a66f`, `f9d4424`) remain permanently in `master`'s history via the
merge commit `5d809bd` — deleting a branch only removes the label, never
the commits it already contributed once merged.

## Summary

| Step | Command | Purpose |
|---|---|---|
| Verify clean | `git status` | Confirm no pending changes before starting |
| Branch | `git checkout -b GitWork` | Create and switch to a feature branch |
| Diverge | separate commits to the same file on both branches | Sets up a real conflict |
| Diff | `git diff master GitWork` | See CLI-level differences before merging |
| Merge | `git merge GitWork` | Attempt to integrate; fails with CONFLICT |
| Inspect | `git status`, conflict markers in the file | Understand what Git couldn't reconcile |
| Resolve | edit file / `git mergetool` (P4Merge) | Produce a single correct version |
| Finish | `git add`, `git commit` | Record the resolved merge |
| Clean up | `.gitignore` for `*.orig`, `git branch -d` | Remove tool cruft and merged branch |
