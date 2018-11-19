(define do-drive #t)

(define (init) #t)
(define (update)
  (when do-drive
    (when (path 0 0 1000 0 1000 100 0.1 0 0 -1000 0 1000 100 0.1)
      (set! do-drive #f))))
