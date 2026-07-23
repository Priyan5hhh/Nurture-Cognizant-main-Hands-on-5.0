# Hands-on Lab: Git Ignore

Objective: explain `.gitignore` and use it to keep unwanted files/folders
(here: `.log` files and a `log/` folder) out of the repository.

## What is `.gitignore`?

`.gitignore` is a plain-text file, placed in a repository (usually at its
root), that tells Git which untracked files and folders it should **not**
offer to stage or commit. Each line is a pattern:

- `*.log` — matches any file ending in `.log`, in any directory.
- `log/` — matches a directory named `log` (the trailing `/` restricts the
  pattern to directories, so it won't accidentally match a file called `log`).
- Lines starting with `#` are comments; blank lines are ignored.

Files matched by `.gitignore` still exist on disk and are usable locally —
Git just stops listing them as "untracked" in `git status` and refuses to
`git add` them unless you force it with `git add -f`. This is normally used
for build output, logs, dependency folders (`node_modules/`), IDE settings,
credentials, etc. — things that are machine-specific or regenerable and
shouldn't be shared through source control.

Important: `.gitignore` only affects **untracked** files. If a file was
already committed before being added to `.gitignore`, Git keeps tracking it;
it must be removed from the index first (`git rm --cached <file>`).

## Steps performed

Demonstrated in [GitDemo/](GitDemo/), a local Git repository with a baseline
commit (`README.md`).

### 1. Create the unwanted file and folder

```
$ echo "Sample application log entry" > app.log
$ mkdir log
$ echo "Sample log file inside log folder" > log/debug.log
```

### 2. Check status BEFORE `.gitignore`

```
$ git status
On branch master
Untracked files:
  (use "git add <file>..." to include in what will be committed)
	app.log
	log/

nothing added to commit but untracked files present (use "git add" to track)
```

Both the `.log` file and the `log` folder show up as untracked — Git wants
to track them.

### 3. Create `.gitignore`

```
# Ignore all files with a .log extension
*.log

# Ignore any folder named "log" (and its contents), at any depth
log/
```

### 4. Check status AFTER `.gitignore`

```
$ git status
On branch master
Untracked files:
  (use "git add <file>..." to include in what will be committed)
	.gitignore

nothing added to commit but untracked files present (use "git add" to track)
```

`app.log` and `log/` no longer appear — only the new `.gitignore` file itself
is untracked. Passing `--ignored` proves Git is deliberately excluding them,
rather than not seeing them:

```
$ git status --ignored
On branch master
Untracked files:
	.gitignore

Ignored files:
  (use "git add -f <file>..." to include in what will be committed)
	app.log
	log/

nothing added to commit but untracked files present (use "git add" to track)
```

### 5. Commit the `.gitignore`

```
$ git add .gitignore
$ git commit -m "Add .gitignore to ignore .log files and log folder"
[master 6088d86] Add .gitignore to ignore .log files and log folder
 1 file changed, 5 insertions(+)
 create mode 100644 .gitignore

$ git status
On branch master
nothing to commit, working tree clean
```

## Verification summary

| Check | Working directory | Local repo (index/staging) | Result |
|---|---|---|---|
| Before `.gitignore` | `app.log`, `log/debug.log` present | untracked, offered by `git status` | would be committed if `git add .` was run |
| After `.gitignore` | files still present on disk | `git status` no longer lists them; `git status --ignored` confirms they're deliberately ignored | `git commit` after `git add .gitignore` leaves a clean working tree — the log file/folder are excluded |

This confirms `.gitignore` works as expected: the ignored files remain on
disk and usable locally, but Git's status/staging no longer surfaces them,
so they will never be pushed to the remote GitLab/GitHub repository.

## Pushing to the remote (once created per the prerequisites)

```
git remote add origin <your-GitDemo-repo-url>
git push -u origin master
```

The pushed history will contain `README.md` and `.gitignore` only —
`app.log` and `log/` were never staged, so they never reach the remote
repository either.
