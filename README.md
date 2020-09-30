# ascii-emoji

[![Build Status][gh-actions-badge]][gh-actions] [![Clojars Project][clojars-badge]][clojars] [![Clojure version][clojure-v]](project.clj)

Use ASCII Emoji, in Clojure!

## Usage

Get started using ASCII emojis.

### Installation

Leiningen/Boot Project file

```clojure
[ascii-emoji "0.1.0"]
```

### Include the Library

In the REPL

```clojure
(require '[ascii-emoji.core :as em])
```

In your application

```clojure
(ns my-app.core
  (:require [ascii-emoji.core :as em]))
```

### Use Emojis - Direct Data

Use the emoji map directly.

```clojure
;; categories
(keys em/emoji-db)

;; all emojis in the "dudes" category
(:dudes em/emoji-db)

;; specific emoji in the dudes category
(get-in em/emoji-db [:dudes :tableflip1])
```

### Use Emojis - Helper Functions

Using the included helper functions to explore/access emojis.

List names

```clojure
;; names of categories
(em/names)

;; names of emojis from the dudes category
(em/names :dudes)
```

Describe categories

```clojure
;; describe all categories
(em/describe)

;; describe specific category
(em/describe :dudes)
```

Show emojis

```clojure
;; show first emoji that matches the keyword (from all categories)
(em/show :tableflip1)

;; show the emoji from the specific category
(em/show :tableflip1 :dudes)
```

Show all emojis

```clojure
;; show the entire emoji map
(em/show-all)

;; show all of the emojis from the category
(em/show-all :dudes)
```

Search Emojis

```clojure
;; search the entire emoji map for the emoji name
(em/search "table")

;; search the specific category for the emoji name
(em/search "table" :dudes)
```

## License

Copyright © 2020 Bill Howe

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
<http://www.eclipse.org/legal/epl-2.0>.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at <https://www.gnu.org/software/classpath/license.html>.

---

### ASCII Data

Some ASCII emojis obtained from: <http://asciimoji.com/>

The [license for that project](https://github.com/hpcodecraft/ASCIImoji/blob/master/LICENSE) is:

"THE BEER-WARE LICENSE" (Revision 42):

Volker Wieban <thesquidpeople@gmail.com> wrote this file.

As long as you retain this notice you can do whatever you want
with this stuff. If we meet some day, and you think this stuff is worth it,
you can buy me a beer in return.

---

<!-- Named page links below: /-->

[gh-actions-badge]: https://github.com/wdhowe/ascii-emoji/workflows/ci%2Fcd/badge.svg
[gh-actions]: https://github.com/wdhowe/ascii-emoji/actions
[clojure-v]: https://img.shields.io/badge/clojure-1.10.0-blue.svg
[clojars]: https://clojars.org/ascii-emoji
[clojars-badge]: https://img.shields.io/clojars/v/ascii-emoji.svg
