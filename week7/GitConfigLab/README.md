# Hands-on Lab: Git Configuration

Objective: get familiar with `git init`, `git status`, `git add`, `git commit`,
`git push`, and `git pull`; configure Git; set Notepad++ as the default editor.

## Step 1: Setup machine with Git configuration

### 1.1 Verify Git client is installed

```
$ git --version
git version 2.50.0.windows.2
```

Git is installed correctly (output shows Git with a version number).

### 1.2 Configure user-level Git identity

```
git config --global user.name "Priyansh Rajput"
git config --global user.email "rajput11priyanshal@gmail.com"
```

### 1.3 Verify the configuration

```
$ git config --global --get user.name
Priyansh Rajput

$ git config --global --get user.email
rajput11priyanshal@gmail.com
```

Global user name and email are set correctly, so commits made on this machine
will be attributed to this identity.

### 1.4 Create the remote project (GitLab / GitHub)

This part happens in the browser, not the terminal:

1. Sign up for a **personal** GitHub or GitLab account (do **not** use
   Cognizant/work credentials).
2. Create a new project/repository named **GitDemo**.
3. Copy the remote URL (HTTPS or SSH) shown on the empty-repo page — it is
   needed for `git remote add origin <url>` in Step 3 below.

## Step 2: Integrate Notepad++ with Git as the default editor

### 2.1 Check if Notepad++ is on the PATH

```
$ notepad++ --version
notepad++ not found in PATH
```

Notepad++ is **not installed** on this machine, so this step could not be
completed here. To do it on a machine that has Notepad++ installed:

1. Find the install folder, typically:
   `C:\Program Files\Notepad++\notepad++.exe`
2. Control Panel → System → Advanced system settings → Environment Variables
3. Under **User variables**, select `Path` → **Edit** → **New** → paste the
   Notepad++ folder path (not the `.exe` filename) → OK on all dialogs.
4. Open a **new** Git Bash window (PATH changes don't apply to already-open
   shells) and re-run `notepad++ --version` to confirm it resolves.

### 2.2 Make Notepad++ the default Git editor

Once step 2.1 works, run:

```
git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"
```

Verify with:

```
git config --global --get core.editor
```

Now `git commit` (with no `-m`) will open Notepad++ for the commit message.

## Step 3: Add a file to a source-code repository

Demonstrated locally in [GitDemo/](GitDemo/):

```
$ git init
Initialized empty Git repository in .../week7/GitConfigLab/GitDemo/.git/

$ git status
On branch master
No commits yet
Untracked files:
	README.md

$ git add README.md
$ git status
On branch master
No commits yet
Changes to be committed:
	new file:   README.md

$ git commit -m "Add README to GitDemo project"
[master (root-commit) 0ce9eba] Add README to GitDemo project
 1 file changed, 5 insertions(+)
 create mode 100644 README.md

$ git log --oneline
0ce9eba Add README to GitDemo project
```

### Pushing to the remote (GitHub/GitLab)

Once the **GitDemo** project from Step 1.4 exists remotely:

```
git remote add origin <your-repo-url>
git branch -M main
git push -u origin main
```

### Pulling changes

To fetch and merge changes made elsewhere (e.g. edited on the GitHub/GitLab
web UI) into this local clone:

```
git pull origin main
```

## Summary of commands covered

| Command | Purpose |
|---|---|
| `git --version` | Confirm Git client is installed |
| `git config --global user.name / user.email` | Set committer identity |
| `git config --global --get <key>` | Verify a config value |
| `git config --global core.editor` | Set default editor (Notepad++) |
| `git init` | Create a new local repository |
| `git status` | Show working-tree/staging state |
| `git add <file>` | Stage a file for commit |
| `git commit -m "<msg>"` | Record staged changes |
| `git log --oneline` | View commit history |
| `git remote add origin <url>` | Link local repo to a remote |
| `git push -u origin main` | Upload commits to the remote |
| `git pull origin main` | Download and merge remote changes |
