[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/-DbjSLZ0)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=13950380)
# CS214 Spring2024 - PA4 - Similarity

## Motivation

In Assignment #3, you ranked songs while accounting for
variations among users and dismissing uncooperative users. Well, ranking is great, but it’s only
one thing you can do with this data. In this assignment, we ask two new questions: 
* what people like the same songs?
* what songs are liked by the same people?
  
With this assignment, the amount of code you have to write per assignment starts to increase, and
the total size of your system increases. Write unit tests for your code (including code
from previous assignments); the time will be well spent.

## Task

In Assignment #2, you ranked songs. In Assignment #3, you extended that to adjust for individual
differences in the number of songs ranked and ranking scales among users. In Assignment #4 you
will measure normalized response similarity among songs and among users. How do you measure
similarity? As follows: given an array of normalized scores x1, x2, …, xn and another array of
normalized scores y1, y2, …, yn, the similarity of the two normalized arrays is Σxiyi for all i. In
other words, it is the sum of the element-wise product. Statistically, this is a similarity measure
because the arrays have been normalized. Also, it requires that the two arrays be the same
length. That’s why we used ‘0’ for the signal value. Any entry with a signal value will contribute
nothing to the final similarity measure.

The input files for Assignment #4 are the same as in Assignment #3: the first input file contains
the names of songs, while the second contains rankings. This time there are two output files,
however, so there are a total of four command-line arguments. Argument #3 is the name of the
file to write song similarity data to; Argument #4 is the name of the file for user similarity data.

The output of your program is two 2D arrays of similarity scores. The first array contains
similarity scores among songs and is written to the file named in command-line argument #3.
If there are N songs, then the array should be NxN. The first row contains the similarity between
song #1 and every other song in order (so the first element of this array is the similarity of song
#1 to itself). The second row contains similarity scores between song #2 and every other song,
and so on. In general, element [i,j] is the similarity in responses of songs i and j.
The second array is the same thing, only for users, not songs, and it is written to the file named in
command-line argument #4. Thus if there are M cooperative users, there should be M rows of
length M, where element [i,j] is the similarity in responses of users i and j.

Example
----------------
Raw Scores    
| |  |  | |
| :--------------: | :------------: | :------------------------: | :---------------:|
| 1 | 5 | 4 | 5 |
| 2 | 3 | 3 | 3 |
| 3 | 1 | 2 | 0 |

Normalized Scores
| | | | |
| :--------------: | :------------: | :------------------------: | :---------------:|
| -1 | 1 | 1 | 1/√2 |
| 0 | 0 | 0 | -1√2 |
| 1 | -1 | -1 | 0 |

Similarity Matrix for Songs
| | | |
| :--------------: | :------------: | :------------------------: |
| 7/2 | -1/2 | -3 |
| -1/2 | 1/2 | 0 |
| -3 | 0 | 3 |

Similarity Matrix for Users
| | | | |
| :--------: | :---------: | :-------: | :-------: |
| 2 | -2 | -2 | -1/√2 |
| -2 | 2 | 2 | 1/√2 |
| -2 | 2 | 2 | 1/√2 |
| -1/√2 | 1/√2 | 1/√2 | 1 |


Don’t get fancy in terms of I/O. Just write similarity scores as double values with the default
formatting, and put a single space between each value and a new line at the end of every row

## Submitting your work

Your submission must be in the main branch of your `GitHub` repository.

## Grading your home work

Run your code with the following command:

```
gradle run -q --args="'input_files/songFileName' 'input_files/ratingsFileName' 'output_files/songOut' 'output_files/userOut'"
```
We will pull your code from you repository. It must contain, at a minimum two
`Java` files. One **must** be named `CS_214_Project.java`. We will run your program by
starting it with that class name. The second file **must** be named
`CS_214_Project_Tester.java`. This will be used to run your `JUnit` tests. All future
assignments **must** contain these two files, although they may contain
different code as needed by the particulars of that assignment.

~~~~
public class CS_214_Project {
  public static void main (String[] args) {
    // your code goes here
    // You may just call the main of some other class you wrote.
  }
}
~~~~

~~~~
import org.junit.Test;

// other imports as needed

public CS_214_Project_Tester {
  @Test
  public void test1_OrWhateverNameMakesSense() {
    // code for your first test
  }

  @Test
  public void test2_OrWhateverNameMakesSense() {
    // code for your second test
  }
}
~~~~
Part of your grade for this assignment will be based on your test coverage. After successfully running `gradle test` your test coverage will be available for display at `/build/jacocoHtml/index.html`. You must have a minimum of 50% coverage to get full points for this portion of your grade.
## Hints
* Reference for resolving **SKIPPED** test bug: [PowerPoint](resources/SystemExitStrategy.pptx)
* Roughly half the similarity scores should be negative.
* The array should be symmetric, i.e. [1,2] should be the same as [2,1]
* The diagonal values should always be positive.

## Polices

All work you submit must be your own. You may not submit code written by a
peer, a former student, or anyone else. You may not copy or buy code from the
web. The department academic integrity policies apply.

You may not submit your program late. To receive credit, it must be submitted by the due date (with a 7 day extension). The exception is an unforeseeable emergency, for example a medical crisis or a death in the immediate family. If an unforeseeable emergency arises, talk to the instructor.
