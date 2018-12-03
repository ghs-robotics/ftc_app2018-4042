(define do-drive #t)

(define (init) #t)
(define (update)
  (when do-drive
    (when (path 0 0 1500 0 2000 200 0.1 0 0 1500 0 2000 200 0.1)
      (set! do-drive #f))))
