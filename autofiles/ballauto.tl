(define state 'run-motor)

(define (init) #t)
(define (update)
  (cond
    ([symbol=? state 'run-motor]
     (unless (path 0 0 -1000 0 1000 300 0.1)
       (set! state 'run-backward)))
    ([symbol=? state 'run-backward]
     (unless (path 0 0 1000 0 1000 300 0.1)
       (set! state 'run-motor)))
  )
)
