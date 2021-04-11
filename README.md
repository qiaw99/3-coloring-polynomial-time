# Understanding and implementation of the algorithm for three-coloring in triangle-free planar graphs

# Related information

- Bachelor thesis at Freie Universität Berlin
- From work group theoretical computer science
- Supervised by Prof. Dr. [László Kozma](http://page.mi.fu-berlin.de/lkozma/)
- The original paper from [Zdenek Dvorak, Ken-ichi Kawarabayashi, Robin Thomas](https://arxiv.org/abs/1302.5121)

# Abstract

Grötzsch's theorem affirms that every planar graph G is 3-colorable in which there is no triangle. Dvorak, Kawarabayashi, and Thomas have designed a linear-time algorithm relying on the Grötzsch's theorem to find a proper 3-coloring of the given graph.  Compared to Kowalik who used a data structure called "Short Path Data Structure (SPDS)", it avoids complex data structures, which makes it easier to implement. However, because of the difficulty of their paper and complicated proofs, it's really hard to understand it. So we will explain their main idea and proofs with more illustrations and in detail. 

# Main idea

After knowing these beneficial definitions, we will introduce five reducible configurations, also called multigrams. With such multigrams, we can reduce the size of graph G to have a smaller graph G' by identifying some vertices. Meanwhile, although we get a smaller graph G', its coloring can be reconstructed to the coloring of Gin constant time. The algorithm detects recursively such multigrams with certain constraints so that the size of graph will always get smaller each time until it's so simple to find the corresponding coloring. Next, according to the resulted coloring, we can reconstruct the coloring of its previous graph step by step. In the end, we'll get the proper coloring of the input graph. 

# The algorithm

The brute force method is that we start with a vertex $v \in V$ and just go through all its adjacent vertices which should be pairwise distinct. In this step, we will use multiple inner for-loops to achieve that. Notice that the case pentagram is kind of special. We need to check additionally whether $v_1, v_2, v_3, v_4$ have degree exactly three. Once we detect a multigram, then we should check the condition whether the found multigram is safe or not.

- Found multigram is tetragram/hexagram. We can use the same way as above by using two for-loops to go through all neighbors and neighbors of neighbors so that we can check that there is no path of length at most three from $v_1$ to $v_3$ which is not part of the tetragram/hexagram. This can be done in $\mathcal{O}(m^2)$ time, where $m = |E|$.
- Found multigram is pentagram. As mentioned in the former case, we can also use two for-loops to check whether there is no path in $G \backslash \{v_1, v_2, v_3, v_4\}$ of length at most three from $x_2$ to $v_5$, and every path in $G \backslash \{v_1, v_2, v_3, v_4\}$ of length at most three from $x_3$ to $x_4$ has length exactly two. This can be done in $\mathcal{O}(m^2)$ time as well.

# Used package

```java
import org.apache.commons.lang3.SerializationUtils;
```

Details find [here](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/SerializationUtils.html).

This package is mainly for deep copy in java. 

