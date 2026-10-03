# University Student Record and Campus Route Management System

## Overview

A Java console application for managing student records, service requests, recent actions, and campus locations and routes. It demonstrates linked lists, a stack, a queue, a binary search tree, hashing, and a graph.

## Requirements and Data Structures

- Student records contain an ID, name, programme, and marks.
- `StudentLinkedList` supports adding, updating, deleting, searching, and displaying student records. Duplicate and blank IDs are rejected.
- `ActionHistoryStack` stores recent actions with last-in, first-out behavior.
- `ServiceRequestQueue` processes service requests in arrival order.
- `StudentBST` stores students by ID and displays them in ID order.
- `StudentHashTable` uses a hash function and separate chaining to search students by ID.
- `CampusGraph` stores locations in an adjacency list, adds and removes locations and roads, displays connections, and traverses connected locations using BFS.
- `Main` provides the menu and validates menu choices, required text, and marks from 0 to 100.

Campus connections are two-way. Marks are treated as values from 0 to 100; confirm this range with the course lecturer if your module uses a different scale.

## Compile and Run

From the project folder, using Java 8 or later:

```powershell
javac *.java
java Main
```

Compiled `.class` files are excluded from Git by `.gitignore`.

## Group Members

Replace every placeholder below with correct information before submission. Record actual contributions after the work has been completed; do not leave placeholders in the submitted README.

| Member | Full name | Student ID | Responsibility | Actual individual contribution |
| --- | --- | --- | --- | --- |
| Member 1 | [ENTER NAME] | [ENTER ID] | Linked list and student-record management | [DESCRIBE WORK COMPLETED] |
| Member 2 | [ENTER NAME] | [ENTER ID] | Stack, queue, and related operations | [DESCRIBE WORK COMPLETED] |
| Member 3 | [ENTER NAME] | [ENTER ID] | BST and hashing/search functionality | [DESCRIBE WORK COMPLETED] |
| Member 4 | [ENTER NAME] | [ENTER ID] | Graph, campus locations/connections, and BFS | [DESCRIBE WORK COMPLETED] |

All members should contribute to integration, validation, testing, debugging, documentation, GitHub collaboration, and the completed project. Update each member's contribution to reflect what they actually did.

## Suggested Test Checklist

- Add valid students; try a duplicate ID, blank input, and marks below 0 or above 100.
- Update and delete existing records; try an ID that does not exist.
- Compare linked-list display with BST order and hash search results.
- Add multiple service requests and confirm they process in arrival order.
- Review recent actions and confirm the newest action appears first.
- Add campus locations and roads; try a duplicate location, missing endpoint, duplicate road, and unavailable road.
- Display the graph and run BFS from a connected location.

## Collaboration and Submission

Each member should work on a separate Git branch, commit their changes, push the branch, and open a pull request for review and merging. The group must provide a merged demonstration video under 15 minutes, with every member's face clearly visible throughout their contribution.

Submit the complete project and video through the designated LMS link before the confirmed deadline. The assignment states 29 September but does not specify a year, so confirm the applicable deadline with the lecturer or LMS. If Google Drive is needed, upload the complete project, grant Editor access to `asanka.r@sltc.ac.lk` and `kaushika.w@sltc.ac.lk`, verify both permissions, put the folder link in a `.txt` file, and submit that file through the LMS. An email is not a substitute for LMS submission.