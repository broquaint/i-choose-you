# i-choose-you

A small program designed to solve the paradox of choice when it comes
to choosing which game to play.

## Usage

Either `lein run` or `lein uberjar` and apply the resulting `.jar` as
appropriate.

Ensure this is run from a directory containing a `config.edn`.

### Configuration

The `config.edn` file needs to be created, most simply by copying the
`config.edn.example`. Both or either of the lists under `:shortcuts` or
`:directories` needs to be populated.

* `:shortcuts` - One or more directories containing shortcuts to games.
* `:directories` - One or more directories containing further directories with 
  games in them. They will be searched recursively for any executables under
  them. A prime example would be the Steam directory.

## License

Copyright © 2012, 2013 Dan Brook

Distributed under the Eclipse Public License, the same as Clojure.
