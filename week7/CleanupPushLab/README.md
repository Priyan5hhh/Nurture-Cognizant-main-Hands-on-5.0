# Hands-on Lab: Clean Up and Push Back to Remote Git

Objective: explain and practice cleaning up local state and pushing pending
work from a feature/hands-on lab back to a remote Git repository.

This lab continues on from the conflict-resolution lab's repository:
[../ConflictResolutionLab/GitDemo/](../ConflictResolutionLab/GitDemo/)
(hands-on ID "Git-T03-HOL_002" is the pending work referenced in step 4 —
i.e. the merge-conflict resolution and `.gitignore` commits already made
there).

## 1. Verify master is in a clean state

```
$ git status
On branch master
nothing to commit, working tree clean
```

Working tree is clean — no uncommitted changes, nothing left to stash or
commit before interacting with the remote.

## 2. List all available branches

```
$ git branch -a
* master
```

Only `master` remains locally (the `GitWork` feature branch from the prior
lab was already deleted after being merged in). No `remotes/origin/...`
entries appear yet because no remote has been configured for this repo.

## 3. Pull the remote Git repository to master

Once a remote (e.g. a GitHub/GitLab "GitDemo" project) exists and is linked:

```
git remote add origin <your-GitDemo-repo-url>   # first time only
git pull origin master
```

`git pull` is `git fetch` (download remote commits) followed by `git merge`
(integrate them into the current branch) in one step. If the remote already
has commits master doesn't have (e.g. changes made through the GitHub/GitLab
web UI, or by a teammate), this brings them in — and could itself produce a
conflict, resolved the same way as in the previous lab, before continuing.

If the remote is brand new and empty, `git pull origin master` will report
`couldn't find remote ref master` — expected, since nothing has been pushed
yet.

## 4. Push the pending changes to the remote repository

```
git push -u origin master
```

`-u` (`--set-upstream`) links the local `master` branch to `origin/master`
so future `git push` / `git pull` calls on this branch don't need the
remote/branch names repeated. This uploads every local commit not yet on
the remote — in this case, the full history from the conflict-resolution
lab: the initial commit, the diverging `hello.xml` commits on `master` and
`GitWork`, the merge-conflict resolution commit, and the `.gitignore`
commit.

## 5. Observe if the changes are reflected in the remote repository

Two ways to confirm the push landed:

**A. Command line** — compare local and remote refs:

```
git log --oneline --graph --decorate --all
```

After a successful push, `origin/master` will appear decorating the same
commit as `master` (e.g. `(HEAD -> master, origin/master)`), confirming
the remote pointer now matches local.

**B. Web UI** — open the repository page on GitHub/GitLab and check:
- The commit list/history matches `git log` locally (same messages, same
  count).
- The file browser shows the final state: `README.md`, `hello.xml`,
  `.gitignore` — with `hello.xml.orig` absent (correctly excluded by
  `.gitignore`).
- The default branch shown is `master` with the latest commit
  ("Ignore .orig backup files left by merge tools").

## Cleanup checklist recap

Before pushing, it's good practice to always run through:

| Check | Command | What it catches |
|---|---|---|
| Working tree clean | `git status` | Uncommitted edits that would be left behind |
| No stray branches | `git branch -a` | Feature branches that were merged but not deleted |
| No stray untracked files | `git status` / `git clean -ndx` | Build artifacts, tool backup files (`.orig`, etc.) not yet in `.gitignore` |
| Up to date with remote | `git pull` before `git push` | Avoids a rejected push / unnecessary merge conflict |

`git clean -ndx` (dry run) lists untracked and ignored files that `git clean`
would delete — useful to sanity-check before ever running the real
`git clean -fdx`, which is destructive and was **not** run here since it
would permanently delete untracked files (nothing in this repo warranted it).
