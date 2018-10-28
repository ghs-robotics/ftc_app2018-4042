(define do-drive #t)

(define (init) #t)
(define (update)
  (when do-drive
    (when (drive 1 1 0 1)
      (set! do-drive #f))))