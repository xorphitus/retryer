# Retryer

A Clojure library designed to retrying a failed code block.

This is inspired by [Retryable Ruby gem](https://github.com/kamui/retriable).


## Usage

```clj
(retryer
 (fn [n] (http-request n))
 :on FooException
 :tries 3)
```

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
