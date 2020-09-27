# ascii-emoji

[![Build Status][gh-actions-badge]][gh-actions] [![Clojars Project][clojars-badge]][clojars] [![Clojure version][clojure-v]](project.clj)

Use ASCII Emoji, in Clojure!

## Usage

### Installation

Leiningen/Boot Project file

```clojure
[ascii-emoji "0.1.0-SNAPSHOT"]
```

### Start Using

In the REPL

```clojure
(require '[ascii-emoji.core :as emoji])
```

In your application

```clojure
(ns my-app.core
  (:require [ascii-emoji.core :as emoji]))
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
